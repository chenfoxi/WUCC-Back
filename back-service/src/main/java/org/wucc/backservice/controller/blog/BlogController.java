package org.wucc.backservice.controller.blog;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by foxi.chen on 24/09/20.
 *
 * @author foxi.chen
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Blog/")
public class BlogController {
}
