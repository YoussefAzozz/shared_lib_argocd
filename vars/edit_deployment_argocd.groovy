def call(String image_name) {
  withCredentials([usernamePassword(
    credentialsId: '743c416b-fd33-4994-be23-6c1599d6e213', // Update to your actual Jenkins credential ID
    usernameVariable: 'GIT_USER',
    passwordVariable: 'GIT_TOKEN'
  )]) {
    sh '''#!/bin/bash
    set -e

    # Clone the repo
    git clone https://${GIT_USER}:${GIT_TOKEN}@github.com/YoussefAzozz/argocd-lab.git
    cd argocd-lab

    # Modify the image line in deployment.yml
    sed -i 's|image:.*|image: "${image_tag}"|g' deployment.yml
    cat deployment.yml

    # Commit and push
    git config user.email "jenkins@example.com"
    git config user.name "jenkins-bot"
    git init
    git add deployment.yml
    git commit -m "Update image tag to '$image_name':${BUILD_NUMBER}"
    git push https://${GIT_USER}:${GIT_TOKEN}@github.com/YoussefAzozz/argocd-lab.git HEAD:master
    '''
  }
}
