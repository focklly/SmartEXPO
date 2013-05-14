/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartexpo.controls;

import com.smartexpo.models.Audio;
import com.smartexpo.models.Author;
import com.smartexpo.models.Description;
import com.smartexpo.models.Item;
import com.smartexpo.models.ItemAudio;
import com.smartexpo.models.ItemAuthor;
import com.smartexpo.models.ItemVideo;
import com.smartexpo.models.Video;
import java.awt.ItemSelectable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author tornado718
 */
@WebServlet(name = "InsertItemServlet", urlPatterns = {"/InsertItemServlet"})
public class InsertItemServlet extends HttpServlet {

    @PersistenceContext(unitName = "SmartEXPO_ProjPU")
    EntityManager em;
    @Resource
    private UserTransaction utx;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Logger logger = Logger.getLogger(InsertItemServlet.class.getName());

            logger.log(Level.WARNING, "in insertItemServlet");
            
            
            
            
            
            
            Item item = new Item();
            item.setItemName(request.getParameter("item_name"));
            Description des = new Description();
            des.setContent(request.getParameter("des_content"));
            des.setTitle(request.getParameter("des_title"));
            des.setItemId(item);


            Author author = new Author();
            author.setIntroduction(request.getParameter("author_introduction"));
            author.setName(request.getParameter("author_name"));


            ItemAuthor itemAuthor = new ItemAuthor();
            itemAuthor.setAuthorId(author);
            itemAuthor.setItemId(item);

            Video video = new Video();
            video.setTitle(request.getParameter("video_title"));
            video.setUrl(request.getParameter("video_url"));
            video.setDescription(request.getParameter("video_description"));
            
            ItemVideo itemVideo=new ItemVideo();
            itemVideo.setItemId(item);
            itemVideo.setVideoId(video);

            Audio audio = new Audio();
            audio.setDescription(request.getParameter("audio_description"));
            audio.setTitle(request.getParameter("audio_title"));
            audio.setUrl(request.getParameter("audio_url"));

            ItemAudio itemAudio=new ItemAudio();
            itemAudio.setAudioId(audio);
            itemAudio.setItemId(item);


            utx.begin();
            
            em.persist(item);
            em.persist(des);
            em.persist(itemAuthor);
            em.persist(author);
            em.persist(audio);
            em.persist(itemAudio);
            em.persist(video);
            em.persist(itemVideo);
            
            utx.commit();
            
            GetInfo gi=new GetInfo(em, utx);
            Item item2 = gi.getItemByID(11);
            logger.log(Level.WARNING,item2.getItemName());
            List<Author> authors=gi.getAuthorsByItemID(11);
            for(int i=0;i<authors.size();i++){
                logger.log(Level.WARNING,authors.get(i).getName());
            }

            request.getRequestDispatcher("itemPages/itemInserted.jsp").forward(request, response);
        } catch (NotSupportedException ex) {
            Logger.getLogger(InsertItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(InsertItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(InsertItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(InsertItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(InsertItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(InsertItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(InsertItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}