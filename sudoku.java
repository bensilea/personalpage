﻿package game1;
import java.io.*;
import java.util.Scanner;

/*例子
800000000
003600000
070090200
050007000
000045700
000100030
001000068
008500010
090000400

000000200
012006090
030250001
008100040
005070300
040002900
200034070
070900650
003000000

100007090
030020008
009600500
005300900
010080002
600004000
300000010
040000007
007000300
*/

public class sudoku {
	
	public static String questionPath="src/game1/sudoku3.txt";
//	public static String questionPath="C:\\Users\\songduo\\Desktop\\sudoku3.txt";
	public static int temp[]=new int []{0,0,0,0,0,0,0,0,0};
	public static int temp2[]=new int []{1,2,3,4,5,6,7,8,9};
	Scanner sc= new Scanner(System.in);
	
	public void displayAll(Grid shudu[][]){		//显示盘格
		for(int i=0;i<shudu.length;i++){
			for(int j=0;j<shudu[i].length;j++){
				System.out.print(shudu[i][j].num+" ");
			}
			System.out.println();
		}
	}
	public void initZhen(Grid shudu[][]){	//初始化阵
		for(int i=0;i<shudu.length;i++){
			for(int j=0;j<shudu[i].length;j++){
				shudu[i][j].zhen=i/3*3+j/3+1;
			}
		}
	}
	
	public int count_length(int a[]){//数组非0数的个数
	    int n=0;
	    for(int i=0;i<a.length;i++)
	    {
	        if(a[i]!=0)
	        {
	            n++;
	        }
	    }
	    return n;
	}
	public static void displaytemp(){	//打印temp
		for(int i=0;i<temp.length;i++){
			System.out.print(temp[i]+" ");
		}
		System.out.println();
	}
	public int select(int a[])//如果只有一个非0数则选中它为答案
	{
	    int n=0;
	    for(int i=0;i<9;i++)
	    {
	        if(a[i]!=0)
	        {
	            n=a[i];
	        }
	    }
	    return n;
	}
	
	public void reset_temp()//重置全局数组temp
	{
	    for(int i=0;i<9;i++)
	    temp[i]=0;
	}
	
	public void inputAll(Grid a[][])//手动录入81个数字
	{
	    for(int b=0;b<9;b++)
	    {
	        for(int v=0;v<9;v++)
	        {
	            a[b][v].num=sc.nextInt();
	        }
	    }
	}
	
	public void fill_duobeixuan_lie(Grid a[][])		//多备选情况下的筛选 列
	{
	    for(int i=0;i<9;i++)
	    {
	        for(int j=0;j<9;j++)
	        {
	            if(a[i][j].num==0)
	            {
	                reset_temp();
	                temp=fanzhen(temp);
	                for(int b=0;b<9;b++)
	                {

	                    if(a[b][j].num==0&&b!=i)
	                    {

	                        for(int v=0;v<9;v++)
	                        {
	                            if(a[i][j].beixuan[v]==a[b][j].beixuan[v]||a[i][j].beixuan[v]==0)
	                            {
	                                temp[v]=0;
	                            }
	                        }
	                    }
	                }
	                if(count_length(temp)==1)
	                {
	                    a[i][j].num=select(temp);
	                    for(int q=0;q<9;q++)
	                    a[i][j].beixuan[q]=0;
	                }
	            }
	            }
	        }
	}
	
	public void fill_duobeixuan_hang(Grid a[][])		//行
	{
	        for(int i=0;i<9;i++)
	        {
	            for(int j=0;j<9;j++)
	            {
	            if(a[i][j].num==0)
	            {
	                reset_temp();
	                temp=fanzhen(temp);
	                for(int b=0;b<9;b++)
	                {

	                    if(a[i][b].num==0&&b!=j)
	                    {
	                        for(int v=0;v<9;v++)
	                        {
	                            if(a[i][j].beixuan[v]==a[i][b].beixuan[v]||a[i][j].beixuan[v]==0)
	                            {
	                                temp[v]=0;
	                            }
	                        }
	                    }
	                }
	                if(count_length(temp)==1)
	                {
	                    a[i][j].num=select(temp);
	                    for(int q=0;q<9;q++)
	                    a[i][j].beixuan[q]=0;
	                }
	            }
	            }
	        }
	}
	
	public void fill_duobeixuan_zhen(Grid a[][])
	{
	        for(int i=0;i<9;i++)
	        {
	            for(int j=0;j<9;j++)
	            {
	            if(a[i][j].num==0)
	            {
	                reset_temp();
	                temp=fanzhen(temp);
	                for(int b=0;b<9;b++)
	                {
	                    for(int v=0;v<9;v++)
	                    {
	                        if(a[b][v].zhen==a[i][j].zhen&&(b!=i||v!=j))
	                        {
	                            for(int q=0;q<9;q++)
	                            {
	                                if(a[i][j].beixuan[q]==a[b][v].beixuan[q]||a[i][j].beixuan[q]==0)
	                                {temp[q]=0;}
	                            }
	                        }
	                    }
	                }
	                if(count_length(temp)==1)
	                {
	                    a[i][j].num=select(temp);
	                    for(int q=0;q<9;q++)
	                    a[i][j].beixuan[q]=0;
	                }
	            }
	            }
	        }
	}
	
	public void input(Grid a[][]){	//传递的是数组地址的引用，是吧？
		int b=0;
		int v=0;
		System.out.println("绗嚑琛�");
		b=sc.nextInt();
		System.out.println("绗嚑鍒�");
		v=sc.nextInt();
		System.out.println("鏄嚑?");
		a[b-1][v-1].num=sc.nextInt();
	}
	
	public int[] fanzhen(int a[]){ 	//反阵
		
	    int temp[]=new int [9];
	    for(int b=1;b<10;b++)
	    {
	        int flag=0;
	        for(int v=0;v<9;v++)
	        {
	            if(a[v]==b)
	            {
	                flag=1;
	                break;
	            }

	        }
	        if(flag==0)
	        temp[b-1]=b;
	    }
	    for(int y=0;y<9;y++)
	    a[y]=temp[y];
	    return a;
	}
	
	public void update_beixuan(Grid a[][]){		//更新全部格子的备选
	    for(int b=0;b<9;b++)
	    {
	        for(int v=0;v<9;v++)
	        {
	            if(a[b][v].num==0)
	            {
	                reset_temp();
	                //hangcun
	                for(int i=0;i<9;i++)
	                {
	                    if(a[b][i].num!=0)
	                    {
	                        temp[a[b][i].num-1]=a[b][i].num;
	                    }
	                }
	                //liecun
	                for(int i=0;i<9;i++)
	                {
	                    if(a[i][v].num!=0)
	                    {
	                        temp[a[i][v].num-1]=a[i][v].num;
	                    }
	                }
	                //kuaicun
	                for(int i=0;i<9;i++)
	                {
	                    for(int j=0;j<9;j++)
	                    {
	                        if( (i/3*3+j/3+1)==(b/3*3+v/3+1) )
	                        {
	                            if(a[i][j].num!=0)
	                            {
	                                temp[a[i][j].num-1]=a[i][j].num;
	                            }
	                        }
	                    }
	                }
	                temp2=fanzhen(temp);
	                for(int u=0;u<9;u++)
	                {
	                    a[b][v].beixuan[u]=temp2[u];
	                }

	            }
	        }
	    }
	}
	
	void function1(Grid a[][])
	{
	    update_beixuan(a);
	    for(int q=0;q<80;q++)
	    {
	        fill_only_onebeixuan(a);
	    }
	    reset_temp();
	    fill_duobeixuan_lie(a);
	    update_beixuan(a);
	    reset_temp();
	    fill_duobeixuan_hang(a);
	    reset_temp();
	    update_beixuan(a);
	    reset_temp();
	    update_beixuan(a);
	    fill_duobeixuan_zhen(a);
	    update_beixuan(a);
	    for(int q=0;q<80;q++)
	    {
	        fill_only_onebeixuan(a);
	    }
	    update_beixuan(a);
//	    display(a);
	}
	
	public void fill_only_onebeixuan(Grid a[][]){  //只有一个备选时，填上格子
	    for(int i=0;i<9;i++)
	    {
	        for(int j=0;j<9;j++)
	        {
	            if(count_length(a[i][j].beixuan)==1)
	            {
	                a[i][j].num=select(a[i][j].beixuan);
	            }
	        }
	    }
	        update_beixuan(a);
	}
	
	public static void main(String args[]) throws IOException{
		Grid shudu[][]=new Grid [9][9];
		sudoku s1=new sudoku();
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				shudu[i][j]=new Grid();
			}
		}
		s1.initZhen(shudu);

		File puzzle=new File(questionPath);
		Reader r=new FileReader(puzzle);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;){
				int readtemp=r.read();
				if(readtemp!=10 && readtemp!=-1 && readtemp!=13){
					shudu[i][j].num=readtemp-48;//asc2码
					j++;
				}
			}
		}
		
	    s1.displayAll(shudu);
	    System.out.println();
	    s1.function1(shudu);
	    s1.displayAll(shudu);
	    r.close();
	}

}
