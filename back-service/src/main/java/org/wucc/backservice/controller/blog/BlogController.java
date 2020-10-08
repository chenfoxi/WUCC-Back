package org.wucc.backservice.controller.blog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wucc.backservice.model.dto.BlogDTO;
import org.wucc.backservice.model.dto.DictTypeDTO;
import org.wucc.backservice.model.dto.request.BlogRequest;
import org.wucc.backservice.model.dto.request.SimpleRequest;
import org.wucc.backservice.model.dto.response.CommonResponse;
import org.wucc.backservice.model.pojo.Blog;
import org.wucc.backservice.model.pojo.User;
import org.wucc.backservice.model.pojo.composite.BlogCounterId;
import org.wucc.backservice.model.pojo.composite.BlogTagId;
import org.wucc.backservice.model.pojo.dict.DictType;
import org.wucc.backservice.model.pojo.relationship.BlogDict;
import org.wucc.backservice.model.pojo.relationship.BlogTag;
import org.wucc.backservice.repository.*;
import org.wucc.backservice.repository.custom.CustomBlogRepository;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxi.chen on 24/09/20.
 *
 * @author foxi.chen
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blog/")
public class BlogController {

    final CustomBlogRepository customBlogRepository;
    final BlogRepository blogRepository;
    final UserRepository userRepository;
    final DictTypeRepository dictTypeRepository;
    final BlogTagRepository blogTagRepository;
    final BlogDictRepository blogDictRepository;
    final BlogCounterRepository blogCounterRepository;

    public BlogController(CustomBlogRepository customBlogRepository, BlogRepository blogRepository,
                          UserRepository userRepository, DictTypeRepository dictTypeRepository,
                          BlogTagRepository blogTagRepository, BlogDictRepository blogDictRepository,
                          BlogCounterRepository blogCounterRepository) {
        this.customBlogRepository = customBlogRepository;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.dictTypeRepository = dictTypeRepository;
        this.blogTagRepository = blogTagRepository;
        this.blogDictRepository = blogDictRepository;
        this.blogCounterRepository = blogCounterRepository;
    }

    @PostMapping("/getbloglist")
    public ResponseEntity<?> getBlogList(@Valid @RequestBody BlogRequest blogRequest) {
        Integer postType = blogRequest.getPostType();
        Integer auditStatus = blogRequest.getAuditStatus();
        CommonResponse<List<BlogDTO>> response = new CommonResponse<>();
        try {
            List<BlogDTO> blogDTOList = customBlogRepository.getBlogListByStatusOrderByUpdateTime(postType, auditStatus);
            response.setData(blogDTOList);
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setData(new ArrayList<>());
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getBlog/{id}")
    public ResponseEntity<?> getBlogDetail(@PathVariable Long id) {
        CommonResponse<BlogDTO> response = new CommonResponse<>();
        try {
            BlogDTO blogDTO = customBlogRepository.getBlogDetailsById(id);
            response.setData(blogDTO);
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setData(null);
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getMostView")
    public ResponseEntity<?> getMostViewList() {
        CommonResponse<List<BlogDTO>> response = new CommonResponse<>();
        try {
            List<BlogDTO> blogDTOList = customBlogRepository.getMostViewListTop10();
            response.setData(blogDTOList);
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setData(null);
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/savedraft")
    public ResponseEntity<?> saveDraft(@Valid @RequestBody BlogRequest blogRequest) {
        Long id = blogRequest.getId();
        Long userId = blogRequest.getUserId();
        String title = blogRequest.getTitle();
        String excerpt = blogRequest.getExcerpt();
        String content = blogRequest.getContent();
        String imgUrl = blogRequest.getImgUrl();
        int postType = blogRequest.getPostType();
        int auditStatus = blogRequest.getAuditStatus();
        int commentStatus = blogRequest.getCommentStatus();
        CommonResponse<Boolean> response = new CommonResponse<>();
        try {
            if (userRepository.existsById(userId)) {
                Blog blog = saveBlog(new User(userId), id, title, excerpt,
                    content, imgUrl, postType, auditStatus, commentStatus);
                    if (blog.getId() != null) {
                        response.setData(true);
                        response.setCode(0);
                        response.setErrorMsg("");
                        return ResponseEntity.ok(response);
                    } else {
                        throw new Exception("save failed");
                    }
            } else {
                throw new IllegalArgumentException("user does not exist.");
            }

        } catch (Exception e) {
            response.setData(false);
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private Blog saveBlog(User user, Long id, String title, String excerpt, String content, String imgUrl,
                          Integer postType, Integer auditStatus, Integer commentStatus) {
        if (id == 0L) {
            //new blog
           return blogRepository.save(new Blog(title, content, excerpt, imgUrl, user, commentStatus, postType,
               auditStatus, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        }else {
            //update blog
            BlogDTO blogDTO = customBlogRepository.getBlogDetailsById(id);
            Blog blog = new Blog();
            blog.setId(blogDTO.getId());
            blog.setTitle(blogDTO.getTitle());
            blog.setExcerpt(blogDTO.getExcerpt());
            blog.setContent(blogDTO.getContent());
            blog.setImgUrl(blogDTO.getImageUrl());
            blog.setPostType(blogDTO.getPostType());
            blog.setAuditStatus(blogDTO.getAuditStatus());
            blog.setCommentStatus(blogDTO.getCommentStatus());
            blog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            blog.setCreateTime(blogDTO.getCreateTime());
            blog.setUser(new User(blogDTO.getUserId()));
            blogRepository.updateBlog(blog.getId(), blog.getTitle(), blog.getContent(), blog.getExcerpt(), blog.getImgUrl(),
                blog.getUpdateTime(),blog.getAuditStatus(), blog.getCommentStatus(), blog.getPostType());
            return blog;
        }
    }

    @GetMapping("/getTags")
    public ResponseEntity<?> getTagList(){
        CommonResponse<List<DictType>> response = new CommonResponse<>();
        try {
            // 0 - tag
            List<DictType> tagList = dictTypeRepository.findAllByType(0);
            response.setData(tagList);
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setData(new ArrayList<>());
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/publishBlog")
    public ResponseEntity<?> publishBlog(@Valid @RequestBody BlogRequest blogRequest) {
        Long id = blogRequest.getId();
        Long userId = blogRequest.getUserId();
        String title = blogRequest.getTitle();
        String excerpt = blogRequest.getExcerpt();
        String content = blogRequest.getContent();
        String imgUrl = blogRequest.getImgUrl();
        int commentStatus = blogRequest.getCommentStatus();
        List<Long> tags = blogRequest.getTags();
        List<DictTypeDTO> settings = blogRequest.getSettings();
        CommonResponse<Boolean> response = new CommonResponse<>();
        try {
            if (userRepository.existsById(userId)) {
                //save blog
                Blog blog = saveBlog(new User(userId), id, title, excerpt,
                    content, imgUrl, 0, 0, commentStatus);
                //construct tags
                List<BlogTag> addBlogTag = new ArrayList<>();
                List<BlogTag> deleteBlogTag = new ArrayList<>();
                for (Long tag : tags) {
                    BlogTagId temp = new BlogTagId(blog.getId(), tag);
                    addBlogTag.add(new BlogTag(temp, blog, new DictType(tag),
                        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
                }
                //save permission setting
                List<BlogDict> addBlogSettings = new ArrayList<>();
                List<BlogDict> deleteBlogSettings = new ArrayList<>();
                for (DictTypeDTO setting : settings) {
                    BlogCounterId temp = new BlogCounterId(blog.getId(), setting.getDictId());
                    addBlogSettings.add(new BlogDict(temp, blog, new DictType(setting.getDictId()),
                        setting.getValue(), new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())));
                }
                if (id.equals(0L)) {
                    //new draft, just adding tags
                    List<BlogTag> blogTags = blogTagRepository.saveAll(addBlogTag);

                    List<BlogDict> blogDictList = blogDictRepository.saveAll(addBlogSettings);
                    if (blog.getId() != null
                        && blogTags.size() == addBlogTag.size()
                        && blogDictList.size() == addBlogSettings.size()) {
                        response.setData(true);
                        response.setCode(0);
                        response.setErrorMsg("");
                        return ResponseEntity.ok(response);
                    } else {
                        throw new Exception("save failed");
                    }
                }
                else {
                    //edit blog, need to find which to add, which to delete
                    List<BlogTag> oldBlogTag = blogTagRepository.findAllByBlogId(blog.getId());
                    for (BlogTag blogTag : oldBlogTag) {
                        if (addBlogTag.contains(blogTag)){
                            addBlogTag.remove(blogTag);
                        } else {
                            deleteBlogTag.add(blogTag);
                        }
                    }
                    blogTagRepository.deleteAll(deleteBlogTag);
                    blogTagRepository.saveAll(addBlogTag);

                    List<BlogDict> oldBlogDict = blogDictRepository.findAllByBlog(blog.getId());
                    for (BlogDict blogDict : oldBlogDict) {
                        if (addBlogSettings.contains(blogDict)){
                            if (blogDict.getValue() !=
                                addBlogSettings.get(addBlogSettings.indexOf(blogDict)).getValue()) {
                                blogDictRepository.save(blogDict);
                            }
                        } else {
                            deleteBlogSettings.add(blogDict);
                        }
                    }
                    blogDictRepository.deleteAll(deleteBlogSettings);
                    blogDictRepository.saveAll(addBlogSettings);

                    if(blog.getId().equals(id)) {
                        response.setData(true);
                        response.setCode(0);
                        response.setErrorMsg("");
                        return ResponseEntity.ok(response);
                    } else {
                        throw new Exception("update failed");
                    }
                }
            } else {
                throw new IllegalArgumentException("user does not exist.");
            }
        } catch (Exception e) {
            response.setData(false);
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    // according to different type get counter
    @PostMapping("/getCounterNumber")
    public ResponseEntity<?> getNumber(@Valid @RequestBody SimpleRequest simpleRequest) {
        Long blogId = simpleRequest.getId();

        CommonResponse<Integer> response = new CommonResponse<>();
        try {
            Long dictId = dictTypeRepository.findByType(simpleRequest.getType()).getId();
            int counter = blogCounterRepository.findCounterByBlogIdAndDictId(blogId, dictId);
            response.setData(counter);
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setData(0);
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
