{
  description = "Fleeca Home Banking";

  inputs = {
    nixpkgs.url = "nixpkgs/24.05";
  };

  outputs = { nixpkgs, ... }:
    let
      supportedSystems = [ "x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin" ];
      forEachSupportedSystem = f: nixpkgs.lib.genAttrs supportedSystems (system: f {
        pkgs = import nixpkgs { inherit system; };
      });
    in
    rec {
      devShells = forEachSupportedSystem ({ pkgs, ... }: {
        default = pkgs.mkShell {
          packages = with pkgs; [
            ant
            jdk
          ];
        };
      });

      packages = forEachSupportedSystem ({ pkgs, ... }: {
        default = pkgs.stdenv.mkDerivation {
          pname = "fleeca";
          version = "1.0";

          src = ./.;

          nativeBuildInputs = with pkgs; [
            ant
            jdk
            stripJavaArchivesHook
          ];

          buildPhase = ''
            runHook preBuild
            ant clean
            ant dist
            runHook postBuild
          '';

          installPhase = ''
            runHook preInstall
            install -Dm644 dist/fleeca.jar $out/share/java/fleeca.jar
            runHook postInstall
          '';

          meta = with pkgs.lib; {
            description = "Fleeca Home Banking";
            license = licenses.mit;
          };
        };
      });
    };
}
