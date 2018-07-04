# How to set up CI/CD in GOCE project

## Overview</br>
This document describes how CI/CD (continuous integration/continuous deployment) is set up in Visual Studio Online (aka. VSO, or VSTS - Visual Studio Team Services) specifically for GOCE project.
In GOCE, Azure environment is the chosen deployment target. Therefore, deployment to other cloud providers is not in the scope of either the document or the project.

## Continuous Integration - CI</br>
### Import a build definition</br>
> **Note**</br>
> All build definitions are source controlled in the same repository as this document, specifically `GOCE.CICD` repository, under `.\VSO\BuildDefinitions` folder. Each `*.json` file is corresponding to a build definition in VSTS, and should be imported separately.
1. Go to Builds page in VSTS, hit *Import* button: 
https://omega-software.visualstudio.com/GOCE/_build
![](.\img\ci-builds-mainpage.PNG)

2. Choose a build definition from repository:
![](.\img\ci-builds-import.PNG)

3. When the build definition is loaded, all "problematic" settings are marked as red, so it doesn't allow saving once all settings are properly set.
![](.\img\ci-problematic-settings.PNG)

> **Note**</br>
> At this step, build definition is not actually imported, it is saved once when you explicitly hit "Save" button, which is described the following steps.

4. Select correct repository</br>
Unfortunately at the moment of writing this document, VSTS does not support storing repository in exported build definition files (`*.json` file). Therefore, in this step we need to select the correct repository corresponding to the build definition being imported. List of build defintions and corresponding repositories is as bellow:

Build definitions                                | Repository
----------------------------------------------- | ----------
Build GOCE.Platform                             | GOCE.Main
Build GOCE.Apps.Admin                           | GOCE.Apps.Admin
Build GOCE.Apps.Communication                   | GOCE.Apps.Communication
Build GOCE.Apps.RecommendationsAndNotifications | GOCE.Main
Build GOCE.Apps.Visualization                   | GOCE.VisualizationApp
Build GOCE.Datasources                          | GOCE.Main

![](.\img\ci-select-repo.PNG)

5. Save build definition</br>
Once repository is selected, and all other problematic settings are resolved, "Save" button is enabled. There are 2 options:
- *Save & queue*: save definition and trigger a build to see if the imported build is good
- *Save*: only save definition
![](.\img\ci-save.PNG)

## Continuos Deployment - CD

### Create service principal connection
In order to deploy the project to any Azure environment, it is required to add a valid Service Principal to VSTS, so it can authenticate against that Azure subscription.

This Microsoft document describes how to create a Service Principal (in target Azure portal): https://docs.microsoft.com/en-us/azure/azure-resource-manager/resource-group-create-service-principal-portal
> **Note**</br>
> At step 5, section *Assign application to role*, "Contributor" role must be selected instead of "Reader" role. It is because VSTS requires "Contributor" role to deploy resources to Azure, using our predefined ARM deployment templates (in `ResourceGroupTemplates` folder).

Next, a service principal connection endpoint must be added to VSTS. This Microsoft document describes how to create a service principal connection endpoint: https://docs.microsoft.com/en-us/vsts/build-release/concepts/library/service-endpoints#sep-azure-rm

### Set up variable groups
Variable groups are variables that are grouped together and can be reused in any release definition. In GOCE, each varible group is corresponding to variables of a specific Azure environment. Concretely, there are 2 active Azure environments (Nightly-dev and Dev4), so there are 2 variable groups created. 

In the future, when new Azure environment is introduced, additional variable group should be set up accordingly.

This Microsoft document describes how to set up a varible group: https://docs.microsoft.com/en-us/vsts/build-release/concepts/library/variable-groups

In GOCE project, a varible group should contain the following variables:
Variable | Description
----------------------- | ------------------------------------------------------------------------------------------------
resourceGroupLocation   | Desired location of the resource group created in Azure environment
resourceGroupName       | Desired resource group name
sharedVarsParams        | Relative path to all parameters, where look up folder is `ResourceGroupTemplates` folder of `GOCE.CICD` repository. This is to implement the idea of source control all parameters in same repository, so they are manageable in once place. Example path (used for Nightly-Dev environment): `/env/variables.parameters.nightly-dev.json`.

> **Note**</br>
> Because most of parameters will be different between environments, it is **highly recommended** to create a new parameter file and commit it to `GOCE.CICD` repository.

### Import a Release definition
> **Note**</br>
> All release definitions are source controlled in the same repository as this document, specifically `GOCE.CICD` repository, under `.\VSO\ReleaseDefinitions` folder. Each `*.json` file is corresponding to a release definition in VSTS, and should be imported separately.

1. Import a release definition from Releases page
![](.\img\cd-release-mainpage.PNG)

2. Select a predefined release definition (a `*.json` file)
![](.\img\cd-import.PNG)

3. Resolve problematic settings</br>
After loading a release definition, all problematic settings are marked with red texts, so we can fix.
![](.\img\cd-problematic-settings.PNG)

> **Note**
> At this step, build definition is not actually saved. It is saved once all problematic settings are resolved and "Save" button is explicitly hit. The following steps will describe this procedure in more detail.

4. Select agent queue<br/>
Depends on release definition being set up, agent queue should be selected correctly. 
![](.\img\cd-select-host-agent.PNG)

List of release definitions and corresponding agent queues is as below:

    Release definition                                  | Agent queue
    --------------------------------------------------- | ----------------------
    Deploy GOCE - Admin app                             | Hosted VS2017
    Deploy GOCE - Communication App                     | Hosted
    Deploy GOCE - Datasources                           | Hosted VS2017
    Deploy GOCE - Platform - Auth                       | Hosted VS2017
    Deploy GOCE - Platform - CoreCommand                | Hosted VS2017
    Deploy GOCE - Platform - CoreQuery                  | Hosted VS2017
    Deploy GOCE - Platform - GeoEventSQLViewMaintainer  | Hosted VS2017
    Deploy GOCE - Platform - GMessageSQLViewMaintainer  | Hosted VS2017
    Deploy GOCE - Platform - OpenAPI                    | Hosted VS2017
    Deploy GOCE - R&N App                               | Hosted VS2017
    Deploy GOCE - Visualization                         | Hosted VS2017

5. Select Azure subscription
> **Note**
> Please make sure that a valid service principal endpoint has been added before proceeding with this step

![](.\img\cd-select-subscription.PNG)

6. Save release definition<br/>
Once all problematic settings are resolved, "Save" button is automatically enabled so you can hit it to save the release definition.

### Set up new Azure environment
Prerequisites:
1. Service principal connection to target Azure environment is set up (see [Create service principal connection](#create-service-principal-connection))
2. Corresponding variable group is created (see [Set up variable groups](#set-up-variable-groups))

The following steps need to be done for every Release definition:

1. Edit a release definition
![](.\img\cd-edit.PNG)

2. Clone from any existing environment
![](.\img\cd-clone.PNG)

3. Resolve problematic settings<br/>
After cloning an environment, there are some settings that need to be resolved. To resolve those settings, go to steps setting of the cloned environment:
![](.\img\cd-cloned.PNG)

4. Name environment and select correct subscription
![](.\img\cd-cloned-setting.PNG)

5. Link variable group
![](.\img\cd-link-var-group.PNG)
> **Important**
> As one certain variable group should be applied to only one environment, please make sure that variable group and its corresponding environment should be selected correctly. Selecting incorrect environment or variable group may result in unexpected errors during deployment.

6. Save release definition
![](.\img\cd-clone-save.PNG)