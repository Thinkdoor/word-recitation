/*this is a dictionary project
 * 2018.6.13
 * @author 闫先生
 * function：1.按不认识显示汉字 2.按认识把数据从字典删除 3.按下一个显示下一个单词
 */
package word.project1_0;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class dictionary1_0 extends JFrame{
	MyPanel mp;
	
	public static void main(String[] args) {
		dictionary1_0 a=new dictionary1_0();

	}
	dictionary1_0()
	{
		mp=new MyPanel();

		this.add(mp);
		this.setTitle("Words");
		this.setSize(250, 150);
		this.setLocation(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
class MyPanel extends JPanel implements ActionListener
{
	JButton jb1,jb2,jb3;
	JPanel jp1,jp2;
	JTextField jtf1,jtf2;
	Dictionary dictionary;
	int i;
	MyPanel()
	{
		//添加组件
		i=0;
		dictionary=new Dictionary();
		jb1=new JButton("不认识");
		jb1.addActionListener(this);
		jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jb2=new JButton("认识");
		jb2.addActionListener(this);
		jb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jb3=new JButton("下一个");
		jb3.addActionListener(this);
		jb3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jp1=new JPanel();
		jp2=new JPanel();
		jtf1=new JTextField();
		jtf1.setEditable(false);
		jtf1.setText(dictionary.words.get(0).word);
		jtf2=new JTextField();
		jtf2.setEditable(false);
		//设置布局
		jp1.setLayout(new GridLayout(2,1));
		//添加组件
		this.setLayout(new BorderLayout());
		this.add(jp1);
		this.add(jp2,BorderLayout.SOUTH);
		jp1.add(jtf1);
		jp1.add(jtf2);
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			jtf2.setText(dictionary.words.get(i).character);	//显示单词解释
		}
		else if(e.getSource()==jb2)
		{
			if(i<dictionary.words.size()-1)
			{
				dictionary.words.remove(i);		//从集合中移除该单词
				jtf1.setText(dictionary.words.get(i).word);	//显示下一个单词的英文
				jtf2.setText(null);		//清空单词解释
			}
			else
			{
				jtf1.setText("congratulate you have learned all words");  //单词库尽
				jtf2.setText(null);		//清空单词解释
			}
		}
		else if(e.getSource()==jb3)
		{
			if(i<dictionary.words.size()-1)
			{
				this.i++;	//单词序号加1
				jtf1.setText(dictionary.words.get(i).word);	//显示下一个单词的英文
				jtf2.setText(null);		//清空单词解释
			}
			else
			{
				jtf1.setText("congratulate you have learned all words");  //单词库尽
				jtf2.setText(null);		//清空单词解释
			}
		}
	}
	
}
class Word	//单词类
{
	String word;
	String character;
}
class Dictionary	//字典类
{
	ArrayList <Word> words=new ArrayList<>();	//字典类含有单词的集合
	Dictionary()
	{
		this.addWords();
	}
	public void addWords()
	{
		Scanner input=null;
		Scanner input1=null;
		try {
			input=new Scanner(new File("words.txt"));	//单词的文本文件
			input1=new Scanner(new File("character.txt"));	//汉字的文本文件
			while(input.hasNext()&&input1.hasNext())
			{
				Word word=new Word();
				word.word=input.next();		//单词文本文件赋给单词的英文
				word.character=input1.next();	//汉字的文本文件赋给单词的翻译
				words.add(word);	//把单词添加到单词集合中
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			input.close();	//关闭流
			input1.close();
		}
	}
}