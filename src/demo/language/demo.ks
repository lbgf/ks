���� java.lang.System;
import java.util.ArrayList;
���� java.lang.String;

var a = new String("aaa");  
���� b = ���� String("bbb"); 
���� c = �¤��� String("ccc");

print a;
��ӡ b;
ӡˢ c;

���� d = �¤��� ArrayList(); 
d.add(a);
d.add(b);
d.add(c);
print d.size();

�� ��1 {
	���� ���Է���() {
		��ӡ "��1-->aaaaa";
	}
}
���� ����1 = ���� ��1();
����1.���Է���();

class class1 {
	function test() {
		print "class1-->bbbbb";
	}
}
var object1 = new class1();
object1.test();

���饹 ���饹1 {
	�C�� �ƥ���() {
		ӡˢ "���饹1-->ccccc";
	}
}
���� ���֥�������1 = �¤��� ���饹1();
���֥�������1.�ƥ���();
