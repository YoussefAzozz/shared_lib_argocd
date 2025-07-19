def call(String image_environment) {
  sshagent(['jenkins-key-git']) {
      sh '''
        git config user.email "jenkins@example.com"
        git config user.name "jenkins-bot"
        git clone https://github.com/YoussefAzozz/argocd-lab.git
        git add deployment.yml

        if ! git diff --cached --quiet; then
          git commit -m "Jenkins update for ${image_environment}:${BUILD_NUMBER}"
          git push git@github.com:YoussefAzozz/argocd-lab.git HEAD:master
        else
          echo "No changes to commit"
        fi
      '''
    
}
}
