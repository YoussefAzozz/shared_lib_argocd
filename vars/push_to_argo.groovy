def call(String image_environment) {
  sshagent(['jenkins-key-git']) {
      sh '''
        git config user.email "jenkins@example.com"
        git config user.name "jenkins-bot"
        ssh-keyscan github.com >> ~/.ssh/known_hosts
        '''
      
        dir('argocd-lab/overlays/prod') {
          sh '''
            git config user.email "jenkins@example.com"
            git config user.name "jenkins-bot"
    
            git add kustomization.yml
    
            if ! git diff --cached --quiet; then
              git commit -m "Jenkins update for ${image_environment}:${BUILD_NUMBER}"
              git push git@github.com:YoussefAzozz/argocd-lab.git HEAD:master
            else
              echo "No changes to commit"
            fi
          '''
    }
    
}
}
