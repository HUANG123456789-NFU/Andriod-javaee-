package cqjtu.edu.studentadmin.entity;

public class Root {
	private String name;

	private int no;
	
	public Root(int no,String name){
		this.no=no;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
