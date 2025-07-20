def call(String image_name) {
  sshagent(['jenkins-key-git']) {
    sh '''
      git clone https://github.com/YoussefAzozz/argocd-lab.git
    '''
    dir('argocd-lab') {
    sh """
    # Modify the image line in deployment.yml
    cd overlays/prod
    sed -i 's|name: nginx|name: ${image_name}|' kustomization.yaml
    sed -i 's|newTag: "1.25"|newTag: "${BUILD_NUMBER}"|' kustomization.yaml
    """
  }
  }
}
