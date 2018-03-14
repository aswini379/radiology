![Librehealth](http://librehealth.io/img/logo-small.png)
# Librehealth Radiology Module
***

## 1 Overview

**Welcome to the landing page of Librehealth Radiology!** This repository contains the Librehealth Radiology Module (librehealth-radiology - previously called radiologydcm4chee and forked from the openMRS radiology module) which is a module adding capabilities of a Radiology
Information System (RIS) onto [Librehealth EHR](https://gitlab.com/librehealth/LibreEHR). The project's primary objectives are to develop a information management module for use in a radiology department, by:

1. identifying a radiology department's workflow according to radiology experts
2. composing user stories in the areas of image study scheduling, ordering, reporting, viewing and analytics on behalf of radiologist and non-radiologist users.
3. implementation of the module to run on the [librehealth toolkit](https://gitlab.com/librehealth/lh-toolkit)
4. maintenance and quality control parameters as set by the community

## 2 Librehealth Community
[![pipeline status](https://gitlab.com/librehealth/lh-radiology/badges/master/pipeline.svg)](https://gitlab.com/librehealth/lh-radiology/commits/master)
[![LibreHealth Radiology Forums](https://img.shields.io/badge/librehealth-forum-orange.svg)](https://forums.librehealth.io/)
[![LibreHealth Radiology Chat](https://img.shields.io/badge/librehealth-chat-orange.svg)](https://chat.librehealth.io)
[![LibreHealth Radiology Telegram](https://img.shields.io/badge/librehealth%20radiology-telegram-blue.svg)](http://telegram.me/LibreHealth%20Radiology)
[![LibreHealth Radiology Wiki](https://img.shields.io/badge/librehealth%20radiology-wiki-blue.svg)](https://gitlab.com/librehealth/lh-radiology/wikis/home)

### 2.1 Contributing and participating in the 2017 Google Summer of Code
Contributions are very welcome, we can definitely use your help! Librehealth has been accepted to participate in the Google Summer of Code 2017 for which there is no shortage of mentors and tasks that prospective students can sign up for.  Browse the [chat room](https://chat.librehealth.io/channel/gci) and [forum](https://forums.librehealth.io/c/community/gsoc) for more information on planned tasks and opportunities for students! To get started , claim a
ticket here [GSOC 2017 issues](https://gitlab.com/librehealth/lh-radiology/issues) and happy coding. Look forward to your pull requests

### 2.2 Community and Support
The project is under collaboration with [librehealth.io](http://librehealth.io), an umbrella organization for health IT projects. <br>
Introduce yourself to the community at: <br> [Librehealth Forums](https://forums.librehealth.io/) <br> [Librehealth Chat (Rocket.Chat)](https://chat.librehealth.io)

### 2.3 Understanding the Radiology Workflow
The following resources will help with understanding the basic infrastructure behind radiology workflow and radiology information systems:

* [Informatics - Radiology Workflow](https://www.youtube.com/watch?v=czApoO5N9K8): A youtube video which introduces the concepts behind hospital EHR infrastructure, HL7 and radiology information system by following a patient through the hospital and radiology department.
* [Writing effective user stories](https://www.youtube.com/watch?v=6q5-cVeNjCE): A youtube video which introduces the concepts and framework of developing user stories and outlines the techniques and motivations behind writing effective ones.
* [Overview of Radiology information Systems and EMR](https://www.acr.org/~/media/ACR/Documents/PDF/Advocacy/IT%20Reference%20Guide/IT%20Ref%20Guide%20RISEMR.pdf): A PDF document which outlines the importance of a radiology information system and serves as a good overview for describing core functionalities which can be used to guide the development of user stories prior to implementation of the module.
* [User stories for Radiology Information System](https://librehealth.gitbooks.io/radiology-user-stories/content/)

## 3 Build, Install and Run

For full instructions visit the repository's [wiki page](https://gitlab.com/librehealth/lh-radiology/wikis/home). Java JDK 8 and Maven can be used for building the module. The easiest way to install and run the module is via a Docker Container. Demo data can also be configured.

### 3.1 Build and deploy the OHIF Viewer Meteor app
The /Viewers folder contains the code for the OHIF Viewer, which is a meteor app. The following needs to be done in steps
* Please install meteor using the following instructions - https://www.meteor.com/install
* cd Viewers/OHIFViewer
* npm install
* Linux: ./bin/orthancDICOMWeb.sh OR Windows: ./bin/orthancDICOMWeb.bat

This will deploy OHIF Viewer on http://localhost:3000 and will be available from the Radiology header menu

NB: Make sure that the global property 'Ohif Viewer Url' is changed to http://localhost:3000

## 4 License
MPL v2.0
