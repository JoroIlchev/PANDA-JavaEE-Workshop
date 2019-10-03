package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.viewModels.UserViewModel;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoggedUserBean {

    private UserViewModel userViewModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public LoggedUserBean() {
    }

    @Inject
    public LoggedUserBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.init();
    }

    private void init() {
        String username = (String) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true))
                .getAttribute("username");
        this.userViewModel = this.modelMapper.map(this.userService.findByUsername(username), UserViewModel.class);
    }

    public UserViewModel getUserViewModel() {
        return this.userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

//    public String getPackageAddressById(String id){
//        return this.userViewModel.getPackages().stream()
//                .filter(p -> p.getId().equals(id))
//                .toString();
//    }
}
