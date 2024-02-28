package com.newpark.main.service.other.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.main.service.entity.Feedback;
import com.newpark.pojo.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 反馈表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
public interface IFeedbackService extends IService<Feedback> {

    List<Feedback> feedbackFindByAll(PageInfoVo pageInfoVo);

    Boolean feedbackFindIns(Feedback feedback);

    Boolean feedbackFindUpt(Long feedbackId,String status);
}
