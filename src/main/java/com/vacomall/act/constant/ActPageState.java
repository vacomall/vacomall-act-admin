package com.vacomall.act.constant;

/**
 * 
 * Created by Gaojun.Zhou 2017年3月6日
 */
public enum ActPageState {

	已上线(1, "已上线"),

	维护中(2, "维护中"), 已下线(3, "已下线"), 已删除(4, "已删除");
	private int state = 2;
	private String name;

	private ActPageState(int state, String name) {
		this.state = state;
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public static ActPageState returnType(int state) {
		for (ActPageState stateEnum : ActPageState.values()) {
			if (stateEnum.getState() == state)
				return stateEnum;
		}
		return 维护中;
	}
	public String getName() {

		return this.name;
	}

}
