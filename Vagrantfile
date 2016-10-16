# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "hashicorp/precise32"

  config.vm.provision :shell, path: "startup.sh"

  config.vm.synced_folder ".vagrantconf/.m2","/home/vagrant/.m2"

  config.vm.network "forwarded_port", guest: 8080, host: 8080, auto_correct: true

  config.vm.provider :virtualbox do |vb|
    vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
    vb.memory=1024
  end
end
