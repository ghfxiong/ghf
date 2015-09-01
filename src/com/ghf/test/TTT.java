package com.ghf.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TTT {

	/**
	 * 程序入口
	 * @param 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		TTT tt = new TTT();
		ArrayList<Person> list = new ArrayList<Person>(); //定义集合
		
		InputStreamReader reader = new InputStreamReader(System.in); //从控制台读取输入流
		BufferedReader br = new BufferedReader(reader);
		String str = null;
		
		menu();
		while((str=br.readLine())!=null){//读取输入了内容
			if(str.indexOf("5")==0){//退出
				System.exit(0);
			}
			System.out.println("当前集合元素为：");
			
			tt.showInfo(list);
			System.out.println("---------------------------------------------------");
			String[] strs = str.split(" "); //输入内容按空格分隔
			Person person = new Person(strs[1],Integer.parseInt(strs[2]),strs[3]); //初始化person 将读取的内容赋值到对应的属性
			if(1==Integer.parseInt(strs[0])){ //增加操作
				list.add(person);
				System.out.println("增加后集合元素为：");
				tt.showInfo(list);//打印集合
				menu();
			}else if(2==Integer.parseInt(strs[0])){//删除操作
				tt.delete(person, list);
				System.out.println("删除后集合元素为：");
				tt.showInfo(list);
				menu();
			}else if(3==Integer.parseInt(strs[0])){//修改操作
				tt.update(person, list);
				System.out.println("修改后集合元素为：");
				tt.showInfo(list);
				menu();
			}else if(4==Integer.parseInt(strs[0])){//查询
				Person qperson = tt.query(person.getName(), list);
				System.out.println("查询结果为：");
				System.out.print(" name : "+qperson.getName()); //打印
				System.out.print(" age : "+qperson.getAge());
				System.out.println(" sex : "+qperson.getSex());
				menu();
			}else if(5==Integer.parseInt(strs[0])){//退出
				System.exit(0);
			}
		}
		
		/*Person person1 = new Person("张三",10,"男"); //定义变量person1 类型为Person 并初始化
		Person person2 = new Person("李四",12,"女"); //定义变量person2 类型为Person 并初始化
		Person person3 = new Person("王五",14,"女"); //定义变量person3 类型为Person 并初始化
		//list.add("aaa"); //增加
		//list.add("bbb");//增加
		list.add(person1);
		list.add(person2);
		list.add(person3);
		for(int i=0;i<list.size();i++){//循环条件
			Person obj = list.get(i); //取ArrayList里下标为i的元素
			//查询list里的bbb元素
			if("李四".equals(obj.getName())){//查找name 为李四的元素
				obj.setAge(111); //将李四的age 改成111；
				list.set(i, obj); //更新
			}
			if("王五".equals(obj.getName())){//查找name 为王五的元素
				list.remove(i); //将王五从集合中删除
			}
		}*/
		
		
		
		/*String[][] users ={
			{"张三","数学","60"},
			{"张三","语文","70"},
			{"李四","数学","80"},
			{"李四","语文","90"}
		};
		
		System.out.println(query("李四","数学",users));*/
	}
	/*
	 * 根据姓名查询
	 */
	public Person query(String name,ArrayList<Person> list){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName().equals(name)){
				return list.get(i);
			}
		}
		return null;
	}
	/**
	 * 更新
	 * @param person
	 * @param list
	 */
	public void update(Person person,ArrayList<Person>list){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName().equals(person.getName())){
				list.set(i, person);
			}
		}
	}
	/**
	 * 删除
	 * @param person
	 * @param list
	 */
	public void delete(Person person,ArrayList<Person>list){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName().equals(person.getName())){
				list.remove(i);
			}
		}
	}
	public void showInfo(ArrayList<Person>list){
		if(list.isEmpty()){
			System.out.println("当前集合为空！");
		}
		for(int i=0;i<list.size();i++){
			System.out.print(" name : "+list.get(i).getName()); //打印
			System.out.print(" age : "+list.get(i).getAge());
			System.out.println(" sex : "+list.get(i).getSex());
		}
	}
	public static void menu(){
		System.out.println("---------------------------------------------------");
		System.out.println("输入操作类型：1-增加; 2-删除; 3-修改; 4-查询;5-退出");
		System.out.println("---------------------------------------------------");
		
	}
	/*public static String query(String name,String subject,String[][] info){
		String score = null;
		for(int i=0;i<info.length;i++){ //循环
			if(info[i][0].equals(name)&& info[i][1].equals(subject)){
				score = info[i][2];
				return score;
			}
		}
		return score;
	}*/

}
