package pl.eduweb.java.advanced;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;

public class Main {

    public static void main(String[] args) throws IOException {
        var path = Paths.get("files");

        System.out.println(Files.exists(path));
        System.out.println(Files.isDirectory(path));
        System.out.println(Files.isHidden(path));

        System.out.println(Files.isExecutable(path));
        System.out.println(Files.isReadable(path));
        System.out.println(Files.isWritable(path));
        System.out.println(Files.getPosixFilePermissions(path));

        System.out.println(Files.getOwner(path));
        System.out.println(Files.getLastModifiedTime(path));

        // reading posix attributes
        System.out.println(Files.readAttributes(path, PosixFileAttributes.class).group());
        System.out.println(Files.readAttributes(path, PosixFileAttributes.class).owner());
        System.out.println(Files.readAttributes(path, PosixFileAttributes.class).permissions());
        
        // setting owner
        UserPrincipal userPrincipal = path.getFileSystem().getUserPrincipalLookupService()
                .lookupPrincipalByName("someuser");
        System.out.println(userPrincipal);
        Files.setOwner(path, userPrincipal);

        // setting group
        GroupPrincipal groupPrincipal = path.getFileSystem().getUserPrincipalLookupService()
                .lookupPrincipalByGroupName("somegroup");
        System.out.println(groupPrincipal);
        Files.getFileAttributeView(path, PosixFileAttributeView.class).setGroup(groupPrincipal);
    }

}
