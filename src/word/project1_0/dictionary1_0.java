/*this is a dictionary project
 * 2018.6.13
 * @author ������
 * function��1.������ʶ��ʾ���� 2.����ʶ�����ݴ��ֵ�ɾ�� 3.����һ����ʾ��һ������
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
		//������
		i=0;
		dictionary=new Dictionary();
		jb1=new JButton("����ʶ");
		jb1.addActionListener(this);
		jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jb2=new JButton("��ʶ");
		jb2.addActionListener(this);
		jb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jb3=new JButton("��һ��");
		jb3.addActionListener(this);
		jb3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jp1=new JPanel();
		jp2=new JPanel();
		jtf1=new JTextField();
		jtf1.setEditable(false);
		jtf1.setText(dictionary.words.get(0).word);
		jtf2=new JTextField();
		jtf2.setEditable(false);
		//���ò���
		jp1.setLayout(new GridLayout(2,1));
		//������
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
			jtf2.setText(dictionary.words.get(i).character);	//��ʾ���ʽ���
		}
		else if(e.getSource()==jb2)
		{
			if(i<dictionary.words.size()-1)
			{
				dictionary.words.remove(i);		//�Ӽ������Ƴ��õ���
				jtf1.setText(dictionary.words.get(i).word);	//��ʾ��һ�����ʵ�Ӣ��
				jtf2.setText(null);		//��յ��ʽ���
			}
			else
			{
				jtf1.setText("congratulate you have learned all words");  //���ʿ⾡
				jtf2.setText(null);		//��յ��ʽ���
			}
		}
		else if(e.getSource()==jb3)
		{
			if(i<dictionary.words.size()-1)
			{
				this.i++;	//������ż�1
				jtf1.setText(dictionary.words.get(i).word);	//��ʾ��һ�����ʵ�Ӣ��
				jtf2.setText(null);		//��յ��ʽ���
			}
			else
			{
				jtf1.setText("congratulate you have learned all words");  //���ʿ⾡
				jtf2.setText(null);		//��յ��ʽ���
			}
		}
	}
	
}
class Word	//������
{
	String word;
	String character;
}
class Dictionary	//�ֵ���
{
	ArrayList <Word> words=new ArrayList<>();	//�ֵ��ຬ�е��ʵļ���
	Dictionary()
	{
		this.addWords();
	}
	public void addWords()
	{
		Scanner input=null;
		Scanner input1=null;
		try {
			input=new Scanner(new File("words.txt"));	//���ʵ��ı��ļ�
			input1=new Scanner(new File("character.txt"));	//���ֵ��ı��ļ�
			while(input.hasNext()&&input1.hasNext())
			{
				Word word=new Word();
				word.word=input.next();		//�����ı��ļ��������ʵ�Ӣ��
				word.character=input1.next();	//���ֵ��ı��ļ��������ʵķ���
				words.add(word);	//�ѵ�����ӵ����ʼ�����
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			input.close();	//�ر���
			input1.close();
		}
	}
}