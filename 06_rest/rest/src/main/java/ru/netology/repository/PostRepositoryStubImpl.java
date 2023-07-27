package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
  private final Map<Long, Post> allPost;
  private static AtomicLong allId =new AtomicLong();

  public PostRepositoryStubImpl() {
    allPost = new HashMap<>();
  }

  public List<Post> all() {
    return allPost.values().stream().toList();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(allPost.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long id = allId.addAndGet(1);
      post.setId(id);
      allPost.put(id, post);
    } else if (post.getId() != 0) {
      long id = post.getId();
      allPost.remove(id);
      allPost.put(id, post);
    }
    return post;
  }

  public void removeById(long id) {
    allPost.remove(id);
  }
}