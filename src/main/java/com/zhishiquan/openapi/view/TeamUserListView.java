package com.zhishiquan.openapi.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 团队成员集合View
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamUserListView extends BasePageView {

    List<TeamUserView> teamUserViews;
}
