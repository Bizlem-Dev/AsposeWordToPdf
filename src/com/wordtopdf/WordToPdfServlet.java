package com.wordtopdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordToPdfServlet
 */
public class WordToPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordToPdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out = response.getWriter();
		
		BufferedReader br = request.getReader();
		String line = "";
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine()) != null){
			sb.append(line);
		}
		
		String UrlLink=sb.toString();
		
		if( !isNullString(UrlLink) ){
			String pdfUrl=Methods.wordToPdfMethod(UrlLink);
			out.println(pdfUrl);
		}
	}

	public static boolean isNullString (String p_text){
		if(p_text != null && p_text.trim().length() > 0 && !"null".equalsIgnoreCase(p_text.trim())){
			return false;
		}
		else{
			return true;
		}
	}
	
}
