package panda.domain.models.viewModels;

import java.util.List;

public class UserViewModel {
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    private List<PackageViewModel> packages;
    private List<ReceiptViewModel> receipts;

    public UserViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<PackageViewModel> getPackages() {
        return this.packages;
    }

    public void setPackages(List<PackageViewModel> packages) {
        this.packages = packages;
    }

    public List<ReceiptViewModel> getReceipts() {
        return this.receipts;
    }

    public void setReceipts(List<ReceiptViewModel> receipts) {
        this.receipts = receipts;
    }
}
