/** this is dictionary2_0 class
 * @Time 2018.6.13
 * @author 闫先生
 * @function 1.按“不认识”按钮显示汉字 2.按“认识”按钮把数据从字典删除 3.按“下一个”按钮显示下一个单词
 * @addfunction 1.可以点击保存按钮可以保留文件 2.新增背景图片 3.新增按钮背景图片
 */
package word.project2_0;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class dictionary2_0 extends JFrame implements ActionListener{
	//定义组件
	JPanel imagePanel;  
    ImageIcon background; 
	JButton jb1,jb2,jb3,jb4;
	JPanel jp1,jp2,jp3,jp4;
	JTextField jtf1,jtf2;
	Dictionary dictionary;
	JLabel label;
	int i;
	public static void main(String[] args) {
		dictionary2_0 a=new dictionary2_0();

	}
	dictionary2_0()
	{
		this.createComponent();
		// 内容窗格默认的布局管理器为BorderLayout  
		imagePanel.setLayout(new FlowLayout());  
		imagePanel.add(jtf1);
		imagePanel.add(jtf2);
		imagePanel.add(jp2);
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		this.getLayeredPane().setLayout(null);  
		// 把背景图片添加到分层窗格的最底层作为背景  
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		this.setTitle("Words");
		this.setSize(background.getIconWidth(), background.getIconHeight());
		this.setLocation(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void createComponent()	//创建组件的函数
	{
		//添加组件
				i=0;
				background=new ImageIcon("java.png"); 	//	设置背景图片
				label = new JLabel(background);// 把背景图片显示在一个标签里面
				// 把标签的大小位置设置为图片刚好填充整个面板  
				label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());  
				// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
				imagePanel=(JPanel)this.getContentPane();  
				imagePanel.setOpaque(false);
				dictionary=new Dictionary();
				jb1=new JButton("不认识");
				jb1.addActionListener(this);
				jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //设置鼠标放上去变为小手
				jb1.setIcon(new ImageIcon("不开心.png"));	//按钮上添加图片
				jb2=new JButton("认识");
				jb2.addActionListener(this);
				jb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				jb2.setIcon(new ImageIcon("开心.png"));		
				jb3=new JButton("下一个");
				jb3.addActionListener(this);
				jb3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				jb3.setIcon(new ImageIcon("一般.png"));
				jb4=new JButton("保存");
				jb4.addActionListener(this);
				jb4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				jp1=new JPanel();
				jp1.setOpaque(false);	
				jp2=new JPanel();
				jp2.setOpaque(false);
				jp3=new JPanel();
				jp3.setOpaque(false);
				jp4=new JPanel();
				jp4.setOpaque(false);
				jtf1=new JTextField(30);
				jtf1.setEditable(false);	//设置文本不可编辑
				jtf1.setFont(new Font("黑体",Font.PLAIN,20));	//设置文本字体
				jtf1.setText(dictionary.words.get(0).word);		//设置文本域初始值
				jtf2=new JTextField(30);	//设置文本域宽度
				jtf2.setEditable(false);	
				jtf2.setFont(new Font("黑体",Font.PLAIN,20));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)		//如果点击第一个按钮
		{
			jtf2.setText(dictionary.words.get(i).character);	//显示单词解释
		}
		else if(e.getSource()==jb2)	//如果点击第二个按钮
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
		else if(e.getSource()==jb3) //如果点击第三个按钮
		{
			if(i<dictionary.words.size()-1)  //如果最后一个单词
			{
				this.i++;	//单词序号加1
				jtf1.setText(dictionary.words.get(i).word);	//显示下一个单词的英文
				jtf2.setText(null);		//清空单词解释
			}
			else	//如果是最后一个单词
			{
				jtf1.setText("congratulate you have learned all words");  //单词库尽
				jtf2.setText(null);		//清空单词解释
			}
		}
		else if(e.getSource()==jb4)	//如果点击第四个按钮
		{
			dictionary.saveWords();
			jtf2.setText("进度保存成功！");		//清空单词解释
		}
	}
}
class Word	//单词类
{
	String word;	//单词
	String character;	//翻译
}
class Dictionary	//字典类（所有单词的集合）
{
	ArrayList <Word> words=new ArrayList<>();	//字典类含有单词的集合
	Dictionary()
	{
		this.addWords();
	}
	public void addWords()
	{
		Scanner input=null;
		try {
			input=new Scanner(new File("words.txt"));	//单词的文本文件
			while(input.hasNext())
			{
				Word word=new Word();
				word.word=input.next();		//单词文本文件赋给单词的英文
				word.character=input.next();	//汉字的文本文件赋给单词的翻译
				words.add(word);	//把单词添加到单词集合中
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			input.close();	//关闭流
		}
	}
	public void saveWords()		//保存单词进度函数
	{
		PrintWriter output=null;	//输出流
		int i=0;
		try {
			output=new PrintWriter("words.txt");	//输出流定位的文件
			while(i<words.size())		//把未删除的单词输出到定位文件中
			{
				output.print(words.get(i).word+" "+words.get(i).character+" ");
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			output.close();
		}
	}
}