Instructions for \projinfo 

1. Set up App server & make sure that the following tables are in your database pool
 
 ARTIFACT (
       ARTIFACT_ID_VERSION  int constraint pk_artifact primary key,
       DEPENDENCY_ID        CHAR(18) NULL,
       TEMPLATE_ID          CHAR(20) NULL,
       ARTIFACT_NAME        CHAR(25) NULL,
       ARTIFACT_DESCRIPTION VARCHAR(60) NULL,
       ARTIFACT_LOCATION    VARCHAR(20) NULL
);

CAT_ATTR (
       CAT_ATTR_TEXT_VALUE  VARCHAR(20) NOT NULL,
       CAT_ATTR_INT_VALUE   int NOT NULL,
       CAT_ATTR_TYPE        VARCHAR(20) NOT NULL,
       CAT_ATTR_DESCRIPTION VARCHAR(20) NULL
);

DEPENDENCY (
       DEPENDENCY_ID        CHAR(18) constraint pk_depend primary key,
       DEPENDENCY_DESCRIPTION VARCHAR(60) NULL,
       DEPENDENCY_TYPE      VARCHAR(20) NULL
);

PROJECT_ARTIFACT (
       projid     varchar(15),
       ARTIFACT_ID_VERSION  int NOT NULL
);

PROJECT_CONTACTS (
       projid     varchar(15),
       CUSTID               int NOT NULL
);

PROJECT_REQS (
        projid     varchar(15),
       REQ_ID_VERSION       int NOT NULL
);

REQUIREMENT (
       REQ_ID_VERSION       int constraint pk_req primary key,
       CAT_ATTR_TEXT_VALUE  VARCHAR(20) NULL,
       CAT_ATTR_INT_VALUE   int NULL,
       CAT_ATTR_TYPE        VARCHAR(20) NULL,
       REQUIREMENT_COMMENT  VARCHAR(20) NULL
);

TEMPLATE (
       TEMPLATE_ID          CHAR(20) constraint pk_template primary key
);
EAUSER(
   userid      varchar(15),
   name        varchar(30),
   password    varchar(10),
   department  varchar(30),
   emailaddr   varchar(30),
   accesslevel int not null);

PROJ_TEAM(
   userid      varchar(15),
   projid        varchar(15));

 EAPROJ(
   projid     varchar(15),
   name       varchar(30),
   initquote  float,
   discount   float,
   actualrate float,
   DESCRIPTION VARCHAR(60));

2. make sure your path is set correctly

3. build application 
    NOTE: the ProjSelectionServlet needs to be deployed w/ the name given in the uri in web.xml file

4. run your server and make any needed setups & deployments
 such as running the consle and adding the new application, the RDBMS realm, adding users and groups
   -- for exmple the following groups:
       any -> no group
       adm -> admin group
       arc -> architect group
       fin -> finance group
       mng -> manager group
       ada -> admin & architect
       adf -> admin & finance
       adm -> admin & manager
       arm -> architect & manager
       fim -> finance & manager

7. to run the example for example in the webbrowser do  hostname:port/projino/login.jsp example: http:\\localhost:7001\projino\login.jsp


