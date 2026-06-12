package com.example.blog_app;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    private final BlogService blogService;
    private final BlogRepository blogRepository;
    // private final BlogService blogService;

    public BlogController(BlogRepository blogRepository, BlogService blogService) {
        this.blogRepository = blogRepository;
        this.blogService = blogService;
    }
    // public BlogController(BlogService blogService) {
    // this.blogService = blogService;
    // }

    @GetMapping("/blogs")
    public String blogs(Model model) {
        model.addAttribute("blogs", blogRepository.findAll());
        return "blogs";
    }

    // @GetMapping("/blogs/post")
    // public String post() {
    // return "blogs/post";
    // }

    @PostMapping("/blogs")
    public String newPost(@ModelAttribute BlogForm form) {
        blogService.register(form);
        // model.addAttribute("title", form.getTitle());
        // model.addAttribute("content", form.getContent());
        return "redirect:/blogs";
    }

    @GetMapping("/blogs/post")
    public String post(Model model) {
        model.addAttribute("blogs", blogRepository.findAll());
        return "blogs/post";
    }

    // 詳細
    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blogOpt.get());
        return "blogs/detail";
    }

    @PostMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }

    // 編集
    @GetMapping("/blogs/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/blogs";
        }
        Blog blog = blogOpt.get();

        BlogForm form = new BlogForm();
        form.setTitle(blog.getTitle());
        form.setContent(blog.getContent());
        model.addAttribute("blogForm", form);
        model.addAttribute("blogId", id);
        return "blogs/edit";
    }

    @PostMapping("/blogs/{id}")
    public String update(@PathVariable Long id, @ModelAttribute BlogForm form) {
        blogService.update(id, form);
        return "redirect:/blogs";
    }

    // @PostMapping("/blogs/post")
    // public String create(@ModelAttribute TaskForm form, Model model) {
    // model.addAttribute("title", form.getTitle());
    // model.addAttribute("text", form.getText());
    // return "post";
    // }

}
