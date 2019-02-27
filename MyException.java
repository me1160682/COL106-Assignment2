public class MyException extends Exception{
   String error_msg;
   
   public MyException(String str2) 
   {
   	error_msg=str2;
   }
   public String toString()
   { 
	return error_msg ;
   }
}
