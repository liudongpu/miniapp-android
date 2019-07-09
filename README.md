

### 集成指南

项目根目录 build.gradle的allprojects的repositories添加子项
`
maven {
            // All of React Native (JS, Android binaries) is installed from npm
            url "$rootDir/../node_modules/react-native/android"
        }
`


app目录下的build.gradle添加引用
`implementation "com.facebook.react:react-native:+" // From node_modules`