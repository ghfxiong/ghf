package com.iamghf.web.common;
/**
 * 常量定义
 * @author ghf
 *
 */
public class WebConstant {
	public static interface WEB_JUMP_FLAG {
		/**	不同页面跳转	 */
		public static final String SV_JUMP = "goto_";
		/** 404 */
		public static final String JUMP_404_URL = "/404.jsp";
	}
	/** 类型常量 */
	public static interface CATEGORY_TYPE {
		/** 菜单  */
		public static int CATEGORY_TYPE_MENU = 1;
		/** 分类  */
		public static int CATEGORY_TYPE_TYPE = 2;
		/** 标签 */
		public static int CATEGORY_TYPE_TAG = 3;
		
		/** 发布 */
		public static int STATE_PUBLISH = 1;
		/** 停用 */
		public static int STATE_UN_USER = 2;
	}
	/** 菜单类型 */
	public static interface ARTICLE {
		/** 最新图文 */
		public static int ARTICLE_TYPE_NEW_THUMB = 1;
		/** 点击排行 */
		public static int ARTICLE_TYPE_CLICK = 2;
		/** 最新文章 */
		public static int ARTICLE_TYPE_NEW_SIMPLE = 3;
		/** 推荐文章 */
		public static int ARTICLE_TYPE_RECOM_SIMPLE = 4;
		/** 推荐图文 */
		public static int ARTICLE_TYPE_RECOM_THUMB = 5;
	}
}
