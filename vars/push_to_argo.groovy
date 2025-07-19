def call(String image_environment) {
  withCredentials([usernamePassword(credentialsId: '743c416b-fd33-4994-be23-6c1599d6e213', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
    sh '''#!/bin/bash
      cd argocd-lab
      git config user.email "jenkins@example.com"
      git config user.name "jenkins-bot"
      git init
      git add deployment.yml
      git commit -m "Jenkins update for ${image_environment}:${BUILD_NUMBER}"
      git push https://${GIT_USER}:${GIT_PASS}@github.com/YoussefAzozz/argocd-lab.git HEAD:master
    '''
  }
}
