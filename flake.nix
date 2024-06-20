{
  description = "Fleeca Home Banking";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-24.05";

  outputs = { self, nixpkgs }:
    let
      system = "aarch64-darwin";
      pkgs = import nixpkgs { inherit system; };
    in
    {
      devShells.${system}.default = pkgs.mkShell {
        packages = with pkgs; [
          jdk
        ];
      };

      packages.${system}.default = pkgs.stdenv.mkDerivation {
        name = "fleeca";
        src = ./.;

        buildInputs = [ pkgs.jdk ];

        buildPhase = ''
          mkdir -p classes
          find src -name "*.java" > sources.txt
          javac -d classes @sources.txt
        '';

        installPhase = ''
          mkdir -p $out/bin
          cd classes
          jar cfm $out/bin/fleeca.jar ../manifest.mf $(find . -name "*.class")
        '';

        meta = with pkgs.lib; {
          description = "A simple Java project built with Nix flakes";
          license = licenses.mit;
          maintainers = [ maintainers.mayocca ];
        };
      };
    };
}
