import java.lang.System;

var startTime = System.currentTimeMillis();
var cnt:int = 0;
while (cnt < 100000000) {
	cnt = cnt+1;
}
System.out.println((System.currentTimeMillis() - startTime) + "ms");
