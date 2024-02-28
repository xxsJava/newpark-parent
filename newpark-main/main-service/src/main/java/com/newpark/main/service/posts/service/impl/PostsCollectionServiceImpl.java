package com.newpark.main.service.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newpark.base.model.vo.R;
import com.newpark.core.utils.StringUtils;
import com.newpark.main.service.config.IdGeneratorSnowflake;
import com.newpark.main.service.entity.Posts;
import com.newpark.main.service.entity.PostsCollection;
import com.newpark.main.service.entity.Product;
import com.newpark.main.service.posts.enums.PostsTypeEnum;
import com.newpark.main.service.posts.mapper.PostsCollectionMapper;
import com.newpark.main.service.posts.mapper.PostsMapper;
import com.newpark.main.service.posts.service.IPostsCollectionService;
import com.newpark.main.service.product.controller.ProductController;
import com.newpark.main.service.product.mapper.ProductMapper;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Service
public class PostsCollectionServiceImpl extends ServiceImpl<PostsCollectionMapper, PostsCollection>
    implements IPostsCollectionService {

    @Resource
    private PostsCollectionMapper postsCollectionMapper;

    @Resource
    private PostsMapper postsMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public List<Object> collectionFindAll(PageInfoVo pageInfoVo,Long uId) {
        PageHelper.startPage(pageInfoVo.getPageNo(), pageInfoVo.getPageSize());
        QueryWrapper<PostsCollection> queryWrap = new QueryWrapper<>();
        queryWrap.eq("u_id",uId);
        // 构建posts Query
        QueryWrapper<Posts> queryWrapPosts = new QueryWrapper<>();
        // 构建Product Query
        QueryWrapper<Product> queryWrapProduct = new QueryWrapper<>();
        List<PostsCollection> postsList = postsCollectionMapper.selectList(queryWrap);

        // 收藏内容
        List<Object> postsCollections = new ArrayList<>();
        // 帖子ID
        List<Long> tId = postsList.stream().filter(type -> PostsTypeEnum.POSTS.toString().equals(type.getCType())).map(PostsCollection::getTId)
            .collect(Collectors.toList());
        queryWrapPosts.in(!tId.isEmpty(), "t_id", tId);

        // 商品ID
        List<Long> pId = postsList.stream().filter(type -> PostsTypeEnum.ORDER.toString().equals(type.getCType())).map(PostsCollection::getTId)
            .collect(Collectors.toList());
        queryWrapProduct.in(!pId.isEmpty(), "p_id", pId);
        // 消息ID
        List<Long> msgId = postsList.stream().filter(type -> PostsTypeEnum.MSG.toString().equals(type.getCType())).map(PostsCollection::getTId)
            .collect(Collectors.toList());

        if (!tId.isEmpty()) {
            postsCollections.addAll(postsMapper.selectList(queryWrapPosts));
        }

        if (!pId.isEmpty()) {
            postsCollections.addAll(productMapper.selectList(queryWrapProduct));
        }

        return PageInfo.of(postsCollections).getList();
    }

    @Override
    public Boolean collectionFindIns(PostsCollection postsCollection) {
        postsCollection.setCId(idGeneratorSnowflake.snowflakeId());
        return postsCollectionMapper.insert(postsCollection) > 0;
    }

    //1824*2(32g 16c 10M esc服务器) + 1117(16 8c 5M esc服务器) + 367.48(redis集群) + 1350(数据库主备) +
    @Override
    public Boolean collectionDel(Long cId) {
        Map<String,Object> delMap = new HashMap<>();
        delMap.put("c_id",cId);
        return postsCollectionMapper.deleteByMap(delMap) > 0;
    }
}
