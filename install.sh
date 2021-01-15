#!/usr/bin/env bash
#
# Wscli install script
#

main() {
    check_java

    git clone https://github.com/The-Kid-Gid/wscli "$HOME/.local/bin/wscli" > /dev/null

    add_to_bashrc "# !!! This block added by the wscli install script !!!"
    add_to_bashrc "alias wscli='java $HOME/.local/bin/wscli/src/wscli.java'"
    add_to_bashrc "# !!! End block !!!"    
}

check_java() {
    if ! command -v java &> /dev/null
    then
        echo "Java not found. Exiting..."
        exit 1
    fi
}

add_to_bashrc() {
    echo "$1" >> "$HOME/.bashrc"
}

main