public class SumLongHex {
    public static long decode2(String num) {
	num = num.substring(2);
	long result = 0;
	for (char i : num.toCharArray())
	result = result * 16 + (Integer.parseInt("" + i, 16));
	return result;
    }

    public static void main(String [] args) {
        String s = "";
        for(int i = 0; i < args.length; ++i) {
            s += args[i] + " ";
        }
        String[] t = s.split("\\p{javaWhitespace}+");    

        long ans = 0;
	for (String st : t) {
	if (st.length() > 0)
	if (st.matches("0(x|X)([0-9a-fA-F]+)"))
	    ans += decode2(st);
	else
	    ans += Long.parseLong(st, 10);
	}
        System.out.println(ans);
    }

}