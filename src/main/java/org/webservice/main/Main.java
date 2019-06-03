package org.webservice.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.webservice.accounts.AccountService;
import org.webservice.accounts.UserProfile;
import org.webservice.servlet.AllRequestsServlet;
import org.webservice.servlet.SignInServlet;
import org.webservice.servlet.SignUpServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        // AllRequestsServlet servlet = new AllRequestsServlet();

        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        SignUpServlet signUp = new SignUpServlet(accountService);
        SignInServlet signIn = new SignInServlet(accountService);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //  context.addServlet(new ServletHolder(servlet), "/allreq");
        context.addServlet(new ServletHolder(signUp), "/signup");
        context.addServlet(new ServletHolder(signIn), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);


        server.start();

        System.out.println("Server started!");

        server.join();
    }
}
