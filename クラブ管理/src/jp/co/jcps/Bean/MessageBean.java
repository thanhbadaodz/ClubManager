package jp.co.jcps.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 画面に表示するメッセージのBean
 *
 */
public class MessageBean implements Serializable {

	// メッセージリスト
	private List<String> messageList;

	/**
	 * コンストラクタ
	 *
	 */
	public MessageBean() {
		this.messageList = new ArrayList<>();
	}

	/**
	 * @return messageList
	 */
	public List<String> getMessageList() {
		return messageList;
	}

	/**
	 * @param messageList セットする messageList
	 */
	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}

	/**
	 * @param messageList に追加する message
	 */
	public void addMessageList(String message) {
		this.messageList.add(message);
	}
}
