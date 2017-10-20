package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Livre;
import service.Bibliotheque;
import service.impl.BibliothequeImpl;

/**
 * Servlet implementation class LivreServlet
 */
@WebServlet(name="livreServlet", urlPatterns={ "/livre/lister", "/livre/editer", "/livre/action" })
public class LivreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Bibliotheque biblio;
	//Bibliotheque biblio = new BibliothequeImpl(3, 5);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath();
		String nextView = "/WEB-INF/jsp/";
		Integer id = request.getParameter("id") != null ? Integer.valueOf(request.getParameter("id")) : 0;
		switch (url) {
		case "/livre/lister":
			//request.setAttribute("livres", biblio.listerLivre());
			request.setAttribute("livres", biblio.getLivreDao().findAll());
			nextView += "livres.jsp";
			break;
		case "/livre/editer":
			
			request.setAttribute("livre", biblio.getLivreDao().findOne(id));
			nextView += "livre.jsp";
					
			break;
		case "/livre/action":
			String titre = request.getParameter("titre");
			String auteur = request.getParameter("auteur");
			Integer parution = Integer.valueOf(request.getParameter("parution"));
			if("update".equals(request.getParameter("action"))) {
				Livre l = new Livre(titre, parution, auteur);
				l.setId(id);
				biblio.getLivreDao().update(l);
			}
			if("delete".equals(request.getParameter("action"))) {
			
				biblio.getLivreDao().delete(id);
			}
			
			nextView = "lister";
			break;

		default:
	
		}
		request.getRequestDispatcher(nextView).forward(request, response);
		request.getRequestDispatcher(nextView).include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		biblio = (Bibliotheque)getServletContext().getAttribute("biblio");
	}

}
