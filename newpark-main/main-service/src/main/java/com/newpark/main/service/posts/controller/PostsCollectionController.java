package com.newpark.main.service.posts.controller;


import com.newpark.base.model.vo.R;
import com.newpark.pojo.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/posts-collection")
@Api(value = "collection", tags = "收藏管理")
public class PostsCollectionController {

    @GetMapping("/collectionApi")
    @ApiOperation(notes = "浏览收藏API", value = "浏览收藏API")
    public R<?> collectionFindAll(@Validated PageInfoVo pageInfoVo){
        return R.ok(pageInfoVo);
    }

    @PutMapping("/collectionApi")
    @ApiOperation(notes = "添加收藏API", value = "添加收藏API")
    public R<?> collectionFindIns(){
        return R.ok();
    }

    @DeleteMapping("/collectionApi/{coId}")
    @ApiOperation(notes = "删除收藏API", value = "删除收藏API")
    public R<?> collectionDel(@PathVariable("coId") Long coId){
        return R.ok(coId);
    }

}
