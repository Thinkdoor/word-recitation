/** this is dictionary2_0 class
 * @Time 2018.6.13
 * @author ������
 * @function 1.��������ʶ����ť��ʾ���� 2.������ʶ����ť�����ݴ��ֵ�ɾ�� 3.������һ������ť��ʾ��һ������
 * @addfunction 1.���Ե�����水ť���Ա����ļ� 2.��������ͼƬ 3.������ť����ͼƬ
 */
package word.project2_0;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class dictionary2_0 extends JFrame implements ActionListener{
	//�������
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
		// ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout  
		imagePanel.setLayout(new FlowLayout());  
		imagePanel.add(jtf1);
		imagePanel.add(jtf2);
		imagePanel.add(jp2);
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		this.getLayeredPane().setLayout(null);  
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		this.setTitle("Words");
		this.setSize(background.getIconWidth(), background.getIconHeight());
		this.setLocation(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void createComponent()	//��������ĺ���
	{
		//������
				i=0;
				background=new ImageIcon("java.png"); 	//	���ñ���ͼƬ
				label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
				// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
				label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());  
				// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
				imagePanel=(JPanel)this.getContentPane();  
				imagePanel.setOpaque(false);
				dictionary=new Dictionary();
				jb1=new JButton("����ʶ");
				jb1.addActionListener(this);
				jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //����������ȥ��ΪС��
				jb1.setIcon(new ImageIcon("������.png"));	//��ť�����ͼƬ
				jb2=new JButton("��ʶ");
				jb2.addActionListener(this);
				jb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				jb2.setIcon(new ImageIcon("����.png"));		
				jb3=new JButton("��һ��");
				jb3.addActionListener(this);
				jb3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				jb3.setIcon(new ImageIcon("һ��.png"));
				jb4=new JButton("����");
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
				jtf1.setEditable(false);	//�����ı����ɱ༭
				jtf1.setFont(new Font("����",Font.PLAIN,20));	//�����ı�����
				jtf1.setText(dictionary.words.get(0).word);		//�����ı����ʼֵ
				jtf2=new JTextField(30);	//�����ı�����
				jtf2.setEditable(false);	
				jtf2.setFont(new Font("����",Font.PLAIN,20));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)		//��������һ����ť
		{
			jtf2.setText(dictionary.words.get(i).character);	//��ʾ���ʽ���
		}
		else if(e.getSource()==jb2)	//�������ڶ�����ť
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
		else if(e.getSource()==jb3) //��������������ť
		{
			if(i<dictionary.words.size()-1)  //������һ������
			{
				this.i++;	//������ż�1
				jtf1.setText(dictionary.words.get(i).word);	//��ʾ��һ�����ʵ�Ӣ��
				jtf2.setText(null);		//��յ��ʽ���
			}
			else	//��������һ������
			{
				jtf1.setText("congratulate you have learned all words");  //���ʿ⾡
				jtf2.setText(null);		//��յ��ʽ���
			}
		}
		else if(e.getSource()==jb4)	//���������ĸ���ť
		{
			dictionary.saveWords();
			jtf2.setText("���ȱ���ɹ���");		//��յ��ʽ���
		}
	}
}
class Word	//������
{
	String word;	//����
	String character;	//����
}
class Dictionary	//�ֵ��ࣨ���е��ʵļ��ϣ�
{
	ArrayList <Word> words=new ArrayList<>();	//�ֵ��ຬ�е��ʵļ���
	Dictionary()
	{
		this.addWords();
	}
	public void addWords()
	{
		Scanner input=null;
		try {
			input=new Scanner(new File("words.txt"));	//���ʵ��ı��ļ�
			while(input.hasNext())
			{
				Word word=new Word();
				word.word=input.next();		//�����ı��ļ��������ʵ�Ӣ��
				word.character=input.next();	//���ֵ��ı��ļ��������ʵķ���
				words.add(word);	//�ѵ�����ӵ����ʼ�����
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			input.close();	//�ر���
		}
	}
	public void saveWords()		//���浥�ʽ��Ⱥ���
	{
		PrintWriter output=null;	//�����
		int i=0;
		try {
			output=new PrintWriter("words.txt");	//�������λ���ļ�
			while(i<words.size())		//��δɾ���ĵ����������λ�ļ���
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