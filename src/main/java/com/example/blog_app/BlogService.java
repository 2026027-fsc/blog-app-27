package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

// import java.uitl.List;
// import org.springframework.blogs.Service;

@Service
public class BlogService {
  private final BlogRepository blogRepository;

  public BlogService(BlogRepository blogRepository) {
    this.blogRepository = blogRepository;
  }

  public List<Blog> findAll() {
    return blogRepository.findAll();
  }

  public void register(BlogForm form) {
    if (form.getTitle().isEmpty()) {
      throw new IllegalArgumentException("タイトルを入力してください");
    }
    if (form.getContent().isEmpty()) {
      throw new IllegalArgumentException("本文を入力してください");
    }
    blogRepository.save(new Blog(null, form.getTitle(), form.getContent()));
  }

  public Optional<Blog> findById(Long id) {
    return blogRepository.findById(id);
  }

  public void delete(Long id) {
    blogRepository.deleteById(id);
  }

  public void update(Long id, BlogForm form) {
  blogRepository.update(id, form.getTitle(), form.getContent());
}
}