package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
  private final JdbcClient jdbcClient;

  public BlogRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Blog> findAll() {
    return jdbcClient.sql("SELECT id, title, content FROM blogs")
        .query(Blog.class)
        .list();
  }

  public void save(Blog blog) {
    jdbcClient.sql("INSERT INTO blogs(title,content) VALUES(:title, :content)")
        .param("title", blog.getTitle())
        .param("content", blog.getContent())
        .update();
  }

  public Optional<Blog> findById(Long id) {
    return jdbcClient.sql("SELECT id, title, content FROM blogs WHERE id = :id")
        .param("id", id)
        .query(Blog.class)
        .optional();
  }

  public void deleteById(Long id) {
    jdbcClient.sql("DELETE FROM blogs WHERE id = :id")
        .param("id", id)
        .update();
  }
}

// public class BlogRepository {
// private final DataSource dataSource;

// public BlogRepository(DataSource dataSource){
// this.dataSource = dataSource;
// }

// public List<Blog> findAll() {
// List<Blog> blogs = new ArrayList<>();
// String sql = "SELECT title, text FROM blogs";

// try ( Connection conn = dataSource.getConnection();
// PreparedStatement stmt = conn.prepareStatement(sql);
// ResultSet rs = stmt.executeQuery()) {

// while (rs.next()) {
// blogs.add(new Blog(rs.getString("title"), rs.getString("text")));
// }
// } catch (SQLException e) {
// throw new RuntimeException(e);
// }
// return blogs;
// }
// }