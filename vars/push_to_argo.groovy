def call(String image_environment) {
  sshagent(['jenkins-key-git']) {
    sh '''
      cd argocd-lab
      git config user.email "jenkins@example.com"
      git config user.name "jenkins-bot"
      git add deployment.yml
      git commit -m "Jenkins update for ${image_environment}:${BUILD_NUMBER}"
      git push git@github.com:YoussefAzozz/argocd-lab.git HEAD:master
    '''
  }
}
