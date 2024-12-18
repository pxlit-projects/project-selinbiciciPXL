import { CanDeactivateFn } from '@angular/router';
import { CreatePostComponent } from '../../components/create-post/create-post.component';
import { EditPostComponent } from '../../components/edit-post/edit-post.component';

export const confirmLeaveGuard: CanDeactivateFn<CreatePostComponent | EditPostComponent> = async(component) => {
  if(component.postForm.dirty){
    return window.confirm("are you sure you want to leave the page?");
  }else{
    return true;
  }
  
};


