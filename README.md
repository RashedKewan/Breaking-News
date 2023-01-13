## *Abstract*

We were tasked with creating an application that takes an address in XML format and displays it as an HTML table. We utilized a Maven Java application with Spring Boot and deployed it using Jenkins which we used by setting up a server in AWS. The code for the application was uploaded to a Git repository, and a webhook was established to connect Git and Jenkins. This can be accomplished by either triggering Jenkins with each commit or push, or by having Jenkins pull at regular intervals. To set this up, a plugin for GitHub must be installed in Jenkins, and the "GitHub hook trigger" option must be selected in the Jenkins job configuration.

## *About*
> * The app purpose is to read the “Breaking News” from the given URL :- http://www.ynet.co.il/Integration/StoryRss2.xml.
> * Parses and presents the breaking news XML in an HTML Format.
> * The app is built by intellij idea with Java.

## *Run Locally*
### *Clone the project*
```bash
git clone https://github.com/RashedKewan/YnetBreakingNews.git
```
### *Build the project*
```bash
mvn clean package
```
### *Run Jar File*
```bash
run the jar file: java -jar target/YnetBreakingNews-0.0.1-SNAPSHOT.jar
```
##
> ### *Website Home Page*
> ![](ynet-news-page.png)
##
##
> ### *Jenkins Pipeline Job*
> Create a Jenkins CI/CD pipeline Job to Build and Deploy (Run) the application on a new instance.
> ![](jenkins-pipeline-img.png)
##
##
> ### *Slack Notification*
> Sent slack notification via jenkins job.
>
> ![](slack-notification.png)
