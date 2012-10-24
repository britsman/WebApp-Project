README f�r grupp 7:s Online-Bibliotek

Om ni vill k�ra webappen med full funktionalitet s� finns det ett par steg som m�ste genomf�ras:

1. �ppna netbeans, g� till team->git->clone och anv�nd denna URI: https://github.com/britsman/WebApp-Project.git (netbeans kommer automatiskt importera projektet.)

2. G� till 'Service' tabben och skapa en ny databas med namn LibDB username app password app.

3. Fr�n servicetabben ska du �ven starta glassfish och g� till adminkonsolen (h�gerklicka glassfish och klicka p� 'Start', h�gerklicka igen och ta 'View Domain Admin Console'.

4. N�r du �r inne i adminkonsolen s� ska du g� till 'JavaMail Sessions' i menyn l�ngst till v�nster. Klicka p� 'New...' knappen.

	*Skriv mail/library i 'JNDI Name' f�ltet. 
	*skriv smtp.gmail.com i 'Mail Host' f�ltet. 
	*Skriv Jeff.dit126@gmail.com i 'Default User' och 'Default Return Adress' f�lten.

5. Innan man g�r vidare m�ste man �ven skapa ett par additional properties.

	* Adda en property med mail.smtp.socketFactory.port i f�ltet 'Name' och 465 i f�ltet 'Value'.
	* Adda en property med mail.smtp.port i f�ltet 'Name' och 465 i f�ltet 'Value'.
	* Adda en property med mail.smtp.socketFactory.fallback i f�ltet 'Name' och false i f�ltet 'Value'.
	* Adda en property med mail.smtp.auth i f�ltet 'Name' och true i f�ltet 'Value'.
	* Adda en property med mail.smtp.password i f�ltet 'Name' och chalmers i f�ltet 'Value'.
	* Adda en property med mail.smtp.socketFactory.class i f�ltet 'Name' och javax.net.ssl.SSLSocketFactory i f�ltet 'Value'.

6. G� till 'Project' tabben och �ppna projektet 'Library'. Leta upp klassen GenerateTestData inuti Test Packages mappen, h�gerklicka p� filen och v�lj test file. 

7. h�gerklicka p� projektet 'Library' och v�lj build, sedan h�gerklicka igen och v�lj run.

Online-Biblioteket supportar de senaste versionerna av Chrome, Opera, Firefox, Safari och Internet Explorer.