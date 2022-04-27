import {Component, OnInit} from '@angular/core';
import {SecurityService} from "../../services/security.service";
import {FormControl, FormGroup} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  profileForm = new FormGroup({
    employeeId: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(public securityService: SecurityService,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  registerUser() {
    console.log(this.profileForm.value);
    this.securityService.registerUser(this.profileForm.value)
      .subscribe(() => {
        console.log('User registered');
        this._snackBar.open('User added', 'Close');
      });
  }
}
