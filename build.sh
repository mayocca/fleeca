#!/bin/bash

# Configuration
MAIN_CLASS="dev.yocca.fleeca.Main"
PROJECT_NAME="fleeca-bank"
VERSION="1.0.0"

# Directory structure
SRC_DIR="src/main/java"
LIB_DIR="lib"
BUILD_DIR="build"
CLASSES_DIR="$BUILD_DIR/classes"
DEPS_DIR="$BUILD_DIR/deps"

# Cleanup function
cleanup() {
    rm -rf $BUILD_DIR
    rm -f sources.txt
    rm -f manifest.txt
}

# Cleanup before build
cleanup

# Create build directories
mkdir -p $CLASSES_DIR $DEPS_DIR

# Create manifest
echo "Main-Class: $MAIN_CLASS" > manifest.txt

# Compile source files
find $SRC_DIR -name "*.java" > sources.txt
javac -d $CLASSES_DIR -cp "$LIB_DIR/*" @sources.txt
rm sources.txt

# Extract dependency JARs
cd $DEPS_DIR
for jar in ../../$LIB_DIR/*.jar; do
    jar xf $jar
done
rm -rf META-INF
cd ../..

# Create uber JAR
jar cvfm $BUILD_DIR/$PROJECT_NAME-$VERSION.jar manifest.txt \
    -C $CLASSES_DIR . \
    -C $DEPS_DIR .

# Cleanup intermediate files
rm -rf $CLASSES_DIR $DEPS_DIR manifest.txt