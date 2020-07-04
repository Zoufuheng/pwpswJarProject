package com.pwpew.service.impl;

import com.pwpew.dao.PostDao;
import com.pwpew.dao.impl.PostDaoImpl;
import com.pwpew.entity.TPost;
import com.pwpew.modeldriven.PostMd;
import com.pwpew.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/21 - 14:31
 * @commet:
 */
@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public List<TPost> findPostList() {
        return postDao.findPostList();
    }

    @Override
    public TPost getPostById(int postId) {
        TPost post = postDao.getPostById(postId);
        /*post.getUser().setUserPassword(null);*/
        return post;
    }

    @Override
    public void insertPost(TPost post) {

        postDao.insertPost(post);
    }

    @Override
    public void deletePost(TPost post) {

        postDao.deletePost(post);
    }

    @Override
    public void updatePost(TPost post) {


        postDao.updatePost(post);
    }

    @Override
    public List<TPost> findPostByPage(PostMd postMd, int firstResult, int maxResults) {
        List<TPost> list = postDao.findPostByPage(postMd, firstResult, maxResults);

        for (TPost post : list) {
            post.getUser().setUserPassword(null);
        }

        return list;
    }

    @Override
    public Long findPostCount(PostMd postMd) {
        return postDao.findPostCount(postMd);
    }

    @Override
    public void updateStatueAndEff(PostMd postMd) {

        postDao.updateStatueAndEff(postMd);
    }

    @Override
    public List<TPost> getPostByPostType(String postType, int maxResults, int firstResult) {
        return postDao.getPostByPostType(postType, maxResults, firstResult);
    }

    @Override
    public List<TPost> findPostByNameOrDescribe(String nameOrDescribe, int firestResult, int maxResult) {
        return postDao.findPostByNameOrDescribe(nameOrDescribe, firestResult, maxResult);
    }

    @Override
    public List<TPost> findPostListByUserId(int userId, int firestResult, int maxResult) {
        return postDao.findPostListByUserId(userId, firestResult, maxResult);
    }

    @Override
    public Long findPostCountByUserId(int userId) {
        return postDao.findPostCountByUserId(userId);
    }

}
