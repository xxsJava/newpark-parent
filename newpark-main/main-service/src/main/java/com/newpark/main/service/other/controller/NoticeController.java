package com.newpark.main.service.other.controller;


import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Notice;
import com.newpark.pojo.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 通知表 前端控制器
 * </p>
 * @author xxs18
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/notice")
@Api(value = "notice", tags = "通知功能")
public class NoticeController {

    @PostMapping("/noticeApi")
    @ApiOperation(notes = "通知发布API", value = "通知发布API")
    public R<?> feedbackFindIns(Notice notice){
        return R.ok(notice);
    }

    @GetMapping("/noticeApi")
    @ApiOperation(notes = "通知回调API", value = "通知回调API")
    public R<?> feedbackFindByAll(){

//        title: 'NewPark',
//                body: '我是一条消息',
//                android: {
//            sound: 'msg',
//                    smallIcon: 'ic_launcher',
//                    largeIcon: 'https://new-by-video.oss-cn-beijing.aliyuncs.com/2023/12/01/logo.png',
//                    channelId,
//                    style: { type: AndroidStyle.BIGPICTURE, picture: 'https://new-by-video.oss-cn-beijing.aliyuncs.com/userImage/1638355971556795.jpg' },
//            importance: AndroidImportance.HIGH,
//        },

        Map<String,Object> mapParents = new HashMap<>();
        mapParents.put("title","NewPark");
        mapParents.put("body","我是一条后端的远程通知");

//        Map<String,Object> mapNodes = new HashMap<>();
//        mapNodes.put("sound");
//        mapNodes.put("smallIcon");
//        mapNodes.put("largeIcon");
//        mapNodes.put("");
//        mapNodes.put("");
//        mapParents.put("android",);

        return R.ok(mapParents);
    }

}
