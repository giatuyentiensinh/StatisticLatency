package com.hust;

public class Node {
	private long time;
	private int id;
	private String msg;
	private int idClient = 0;

	public Node(String input) {
		String[] splits = input.split(",");
		this.time = Long.parseLong(splits[0]);
		this.id = Integer.parseInt(splits[1]);
		this.msg = splits[2];
		if (splits.length == 4)
			this.idClient = Integer.parseInt(splits[3]);
	}

	public boolean compare(Node a) {
		if (a.id == this.idClient && a.msg.equals(this.msg))
			return true;
		return false;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	@Override
	public String toString() {
		return "Node [time=" + time + ", id=" + id + ", msg=" + msg
				+ ", idClient=" + idClient + "]";
	}

}