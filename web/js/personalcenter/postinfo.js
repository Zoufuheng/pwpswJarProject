/**
 * 声明函数对象.
 */
var PageController = function() {
	//初始化页面
	this.initPage();
};
/**
 * 通过原型定义相关方法，比如渲染、赋值等等.
 */
PageController.prototype = {
	/**
	 * 初始化页面.
	 */
	initPage: function() {
		//todo
		if (typeof metaModel === 'object') {
			this.metaModel = metaModel;
		}
		if (typeof dataModel === 'object') {
			this.dataModel = dataModel;
		}
	},
	/**
	 * 渲染.
	 */
	render: function() {
		//绑定事件.
		this.bindEvent();
	},
	/**
	 * 绑定事件.
	 */
	bindEvent: function() {
		var btns = $(".btn-group.btn-group-change>button");
		btns.off("click.changeActive").on("click.changeActive", function() {
			$(this).siblings("button").removeClass("active");
			$(this).addClass("active");
		});
	}
	/**
	 * 开始执行.
	 */
};
var controller = new PageController();
controller.render();