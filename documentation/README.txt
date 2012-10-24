README för grupp 7:s Online-Bibliotek

Om ni vill köra webappen med full funktionalitet så finns det ett par steg som måste genomföras:

1. Öppna netbeans, gå till team->git->clone och använd denna URI: https://github.com/britsman/WebApp-Project.git (netbeans kommer automatiskt importera projektet.)

2. Gå till 'Service' tabben och skapa en ny databas med namn LibDB username app password app.

3. Från servicetabben ska du även starta glassfish och gå till adminkonsolen (högerklicka glassfish och klicka på 'Start', högerklicka igen och ta 'View Domain Admin Console'.

4. När du är inne i adminkonsolen så ska du gå till 'JavaMail Sessions' i menyn längst till vänster. Klicka på 'New...' knappen.

	*Skriv mail/library i 'JNDI Name' fältet. 
	*skriv smtp.gmail.com i 'Mail Host' fältet. 
	*Skriv Jeff.dit126@gmail.com i 'Default User' och 'Default Return Adress' fälten.

5. Innan man går vidare måste man även skapa ett par additional properties.

	* Adda en property med mail.smtp.socketFactory.port i fältet 'Name' och 465 i fältet 'Value'.
	* Adda en property med mail.smtp.port i fältet 'Name' och 465 i fältet 'Value'.
	* Adda en property med mail.smtp.socketFactory.fallback i fältet 'Name' och false i fältet 'Value'.
	* Adda en property med mail.smtp.auth i fältet 'Name' och true i fältet 'Value'.
	* Adda en property med mail.smtp.password i fältet 'Name' och chalmers i fältet 'Value'.
	* Adda en property med mail.smtp.socketFactory.class i fältet 'Name' och javax.net.ssl.SSLSocketFactory i fältet 'Value'.

6. Gå till 'Project' tabben och öppna projektet 'Library'. Leta upp klassen GenerateTestData inuti Test Packages mappen, högerklicka på filen och välj test file. 

7. högerklicka på projektet 'Library' och välj build, sedan högerklicka igen och välj run.

Online-Biblioteket supportar de senaste versionerna av Chrome, Opera, Firefox, Safari och Internet Explorer.