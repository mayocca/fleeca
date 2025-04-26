{
  inputs = {
    nixpkgs.url = "nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs =
    {
      self,
      nixpkgs,
      flake-utils,
      ...
    }:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = (import nixpkgs {
            inherit system;
        });

        fleeca = pkgs.stdenv.mkDerivation {
          pname = "fleeca";
          version = "1.0.0";
          src = ./.;

          nativeBuildInputs = with pkgs; [
            ant
            jdk
            makeWrapper
            stripJavaArchivesHook
          ];

          buildPhase = ''
            runHook preBuild
            ant
            runHook postBuild
          '';

          installPhase = ''
            runHook preInstall
            install -Dm644 dist/fleeca.jar $out/share/java/fleeca.jar
            makeWrapper ${pkgs.jdk}/bin/java $out/bin/fleeca \
              --add-flags "-jar $out/share/java/fleeca.jar"
            runHook postInstall
          '';

          meta = with pkgs.lib; {
            description = "A simple bank application";
            homepage = "https://github.com/mayocca/fleeca";
            licenses = with licenses; [
              agpl3Only
            ];
            platforms = platforms.all;
            maintainers = with maintainers; [
              mayocca
            ];
          };
        };
      in
      rec {
        # --- packages ---
        packages.fleeca = fleeca;
        packages.default = packages.fleeca;

        # --- apps ---
        apps.fleeca = flake-utils.lib.mkApp { drv = packages.fleeca; };
        apps.default = apps.fleeca;

        # --- formatter ---
        formatter = pkgs.nixfmt-rfc-style;

        # --- shell ---
        devShells.default = pkgs.mkShell {
          buildInputs = with pkgs; [
            ant
            jdk
          ];
        };
      }
    );
}
